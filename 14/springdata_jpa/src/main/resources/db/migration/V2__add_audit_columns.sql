ALTER TABLE products
    ADD COLUMN created_at timestamptz NOT NULL DEFAULT now(),
    ADD COLUMN last_modified_at timestamptz NOT NULL DEFAULT now();