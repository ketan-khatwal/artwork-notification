CREATE TABLE IF NOT EXISTS account_activities(
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT,
    email VARCHAR(250),
    trackable_id BIGINT,
    trackable_type VARCHAR(250),
    company_id BIGINT,
    event VARCHAR(250),
    old_values JSON,
    new_values JSON,
    resources JSON,
    request JSON,
    additional_filterable_entities JSON
);