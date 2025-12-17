-- Crear tabla jugadores
CREATE TABLE jugadores (
    id SERIAL PRIMARY KEY,
    login VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    correo VARCHAR(30) NOT NULL,
    activo VARCHAR(5) NOT NULL DEFAULT 'true',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE records (
    id SERIAL PRIMARY KEY,                    -- ID autoincremental
    jugador_id INTEGER NOT NULL,             -- ID del jugador
    juego_id INTEGER NOT NULL,               -- ID del juego
    record INTEGER NOT NULL,                 -- Puntuación/récord
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Clave foránea a jugadores
    CONSTRAINT fk_records_jugador 
        FOREIGN KEY (jugador_id) 
        REFERENCES jugadores(id) 
        ON DELETE CASCADE,

    
    -- Restricciones
    CONSTRAINT check_record_positive CHECK (record >= 0),
    
    -- Un jugador no puede tener múltiples records para la misma fecha
    CONSTRAINT unique_jugador_juego_fecha UNIQUE (jugador_id, fecha)
);











