CREATE TABLE technical_process_production_stages
(
    technical_process_id BIGINT NOT NULL,
    production_stage_id BIGINT NOT NULL
);

ALTER TABLE technical_process_production_stages
    ADD CONSTRAINT FK_TECHNICAL_PROCESSES_ID
        FOREIGN KEY (technical_process_id) REFERENCES technical_processes (id);

ALTER TABLE technical_process_production_stages
    ADD CONSTRAINT FK_STAGES_PRODUCTION_ID
        FOREIGN KEY (production_stage_id) REFERENCES stages (id);

INSERT INTO technical_process_production_stages(technical_process_id, production_stage_id)
VALUES (1, 1), (2, 2);