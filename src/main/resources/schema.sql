CREATE TABLE IF NOT EXISTS orders (
                                      id BIGSERIAL PRIMARY KEY,
                                      total NUMERIC(12, 2) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
    );

CREATE TABLE IF NOT EXISTS outbox_events (
                                             id BIGSERIAL PRIMARY KEY,
                                             aggregate_type TEXT NOT NULL,
                                             aggregate_id TEXT NOT NULL,
                                             event_type TEXT NOT NULL,
                                             payload JSONB NOT NULL,
                                             created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    published_at TIMESTAMPTZ NULL,
    publish_attempts INT NOT NULL DEFAULT 0
    );