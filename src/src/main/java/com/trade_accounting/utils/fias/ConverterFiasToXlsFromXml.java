package src.main.java.com.trade_accounting.utils.fias;

import com.trade_accounting.models.entity.fias.FiasAddressModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс предназначен для парсинга xml файла (ФИАС) в xls (Excel) только городов - миллионников,
 * количество улиц в каждом городе ограничено 100.
 */
public class ConverterFiasToXlsFromXml {
    private static final String SOURCE = "/Users/romanvohmin/Downloads/FIAS/AS_ADDROBJ_20210416_b9831bab-6512-48e4-86ad-a5d41cb835c6.XML";
    private static final List<String> billionCities = List.of("Москва", "Санкт-Петербург",
            "Новосибирск", "Екатеринбург", "Нижний Новгород", "Казань", "Челябинск", "Омск",
            "Самара", "Ростов-на-Дону", "Уфа", "Красноярск", "Воронеж", "Пермь", "Волгоград");
    List<String> cityGuids = new ArrayList<>();
    List<String> regionGuids = new ArrayList<>();
    Map<String, Integer> streetCount = new HashMap<>();

    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("БД Адресов");
    int rowNum = 0;

    private void initXls() {
        Row row = sheet.createRow(++rowNum);
        row.createCell(0).setCellValue("aolevel");
        row.createCell(1).setCellValue("formalname");
        row.createCell(2).setCellValue("shortname");
        row.createCell(3).setCellValue("aoguid");
        row.createCell(4).setCellValue("parentguid");
        try (FileOutputStream out = new FileOutputStream("DbAdress.xls")) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
    }

    private void createSheetHeader(HSSFSheet sheet, FiasAddressModel model) {
        Row row = sheet.createRow(++rowNum);
        if (model.getParentguid() == null) {
            model.setParentguid(" ");
        }
        row.createCell(0).setCellValue(model.getAolevel());
        row.createCell(1).setCellValue(model.getFormalname());
        row.createCell(2).setCellValue(model.getShortname());
        row.createCell(3).setCellValue(model.getAoguid());
        row.createCell(4).setCellValue(model.getParentguid());
    }

    public void writeBook() {
        try (FileOutputStream out = new FileOutputStream("DbAdress.xls")) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillbillionCitiesXls() {
        initXls();
        System.out.println("Заполняем города-миллионники");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(SOURCE));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Object")) {
                        String actstatus = startElement.getAttributeByName(new QName("ACTSTATUS")).getValue();
                        String aolevel = startElement.getAttributeByName(new QName("AOLEVEL")).getValue();
                        String name = startElement.getAttributeByName(new QName("FORMALNAME")).getValue();
                        String shortName = startElement.getAttributeByName(new QName("SHORTNAME")).getValue();
                        if (actstatus.equals("1") && billionCities.contains(name)
                                && (aolevel.equals("4") || aolevel.equals("1"))
                                && (shortName.equals("г"))) {
                            cityGuids.add(startElement.getAttributeByName(new QName("AOGUID")).getValue());
                            createSheetHeader(sheet, getModelFromObject(startElement));
                            Attribute attributeParent = startElement.getAttributeByName(new QName("PARENTGUID"));
                            if (attributeParent != null) {
                                regionGuids.add(attributeParent.getValue());
                            }
                        }
                    }
                }
            }
            System.out.println("Города-миллионники заполнены");
        } catch (FileNotFoundException | XMLStreamException exc) {
        }
    }

    public void fillStreetsXls() {
        System.out.println("Заполняем улицы в городах-миллионниках");
        initCount();
        var xmlInputFactory = XMLInputFactory.newInstance();
        try {
            var reader = xmlInputFactory.createXMLEventReader(new FileInputStream(SOURCE));
            while (reader.hasNext()) {
                var xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    var startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Object")) {
                        for (var cityGuid : cityGuids) {
                            if (streetCount.get(cityGuid) >= 100) {
                                continue;
                            }
                            if (startElement.getAttributeByName(new QName("PARENTGUID")) != null) {
                                var actstatus = startElement.getAttributeByName(new QName("ACTSTATUS")).getValue();
                                var aolevel = startElement.getAttributeByName(new QName("AOLEVEL")).getValue();
                                var parentguid = startElement.getAttributeByName(new QName("PARENTGUID")).getValue();
                                if (actstatus.equals("1") && (aolevel.equals("7")) && (parentguid.equals(cityGuid))) {
                                    createSheetHeader(sheet, getModelFromObject(startElement));
                                    streetCount.put(cityGuid, streetCount.get(cityGuid) + 1);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Улицы городов-миллионников заполнены");
        } catch (FileNotFoundException | XMLStreamException exc) {
        }
    }

    public void fillRegionsXls() {
        System.out.println("Заполняем регионы городов-миллионников - " + regionGuids.size());
        var xmlInputFactory = XMLInputFactory.newInstance();
        try {
            var reader = xmlInputFactory.createXMLEventReader(new FileInputStream(SOURCE));
            while (reader.hasNext()) {
                var xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    var startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Object")) {
                        var actstatus = startElement.getAttributeByName(new QName("ACTSTATUS")).getValue();
                        if (!actstatus.equals("1")) {
                            continue;
                        }
                        var aoguid = startElement.getAttributeByName(new QName("AOGUID")).getValue();
                        for (var guid : regionGuids) {
                            if (aoguid.equals(guid)) {
                                createSheetHeader(sheet, getModelFromObject(startElement));
                            }
                        }
                    }
                }
            }
            System.out.println("Регионы городов-миллионников заполнены");
        } catch (FileNotFoundException | XMLStreamException exc) {
        }
    }

    private FiasAddressModel getModelFromObject(StartElement startElement) {
        String actstatus = startElement.getAttributeByName(new QName("ACTSTATUS")).getValue();
        String aolevel = startElement.getAttributeByName(new QName("AOLEVEL")).getValue();
        String name = startElement.getAttributeByName(new QName("FORMALNAME")).getValue();
        String shortName = startElement.getAttributeByName(new QName("SHORTNAME")).getValue();
        String aoguid = startElement.getAttributeByName(new QName("AOGUID")).getValue();
        String parentguid = "";
        if (!aolevel.equals("1")) {
            parentguid = startElement.getAttributeByName(new QName("PARENTGUID")).getValue();
        }
        FiasAddressModel model = FiasAddressModel.builder()
                .aolevel(aolevel)
                .formalname(name)
                .shortname(shortName)
                .aoguid(aoguid)
                .parentguid(parentguid)
                .build();
        return model;
    }

    private void initCount() {
        for (String cityGuid : cityGuids) {
            streetCount.put(cityGuid, 0);
        }
    }

    public static void main(String[] args) {
        ConverterFiasToXlsFromXml xlsFromXml = new ConverterFiasToXlsFromXml();
        xlsFromXml.fillbillionCitiesXls();
        xlsFromXml.fillRegionsXls();
        xlsFromXml.fillStreetsXls();
        xlsFromXml.writeBook();
    }
}
