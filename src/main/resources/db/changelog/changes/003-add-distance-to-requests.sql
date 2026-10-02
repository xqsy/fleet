-- liquibase formatted sql

-- changeset xqsy:2026-09-28-add-distance-to-requests
ALTER TABLE transport_requests add column distance_km integer not null default 1 check (distance_km > 0);