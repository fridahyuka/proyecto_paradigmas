# Piedra Papel Tijera

Proyecto realizado para la Facultad de Ingeniería de la Universidad Autónoma del Estado de México

**Integrantes:**

1. Frida Alejandra Mendoza Martinez
2. Gael González Méndez
3. Angel Yahir Albarrán Cruz
4. Kevin Alejandro Nabor Matias
5. Jesús Emmanuel Díaz Vera
6. Aarón Daniel Díaz Martínez

## Instalación

### Requisitos

1. Java SE. instalado
2. Postgre instalado
   2.1 Es necesario el usuario "postgre" con contraseña "123"

### Pasos

Es necesario crear la base de datos, para ello es necesario ejecutar el siguiente comando el la raíz de este repositorio.

```bash
sudo -u postgres psql -d pptls -f schema.sql

```

# Diagramas UML

### Diagrama de caso de uso

![Diagrama UML de caso de uso ](docs/uml/PPT%20CLASS%20UML%20USE%20CASE.png "Diagrama UML de caso de uso")

## Diagrama de clases

![Diagrama UML de clases](docs/uml/PPT%20UML%20Class%20Diagram.png "Diagrama UML de clases")

## Diagramas de secuencia

![Diagrama UML de secuencia para inicio de sesión](docs/uml/PPT%20CLASS%20UML%20AUTH%20Sequence%20Diagram%20.png "Diagrama UML de secuencia para inicio de sesión")

![Diagrama UML de secuencia para registrarse](docs/uml/PPT%20CLASS%20UML%20Sign%20up%20Sequence%20Diagram.png "Diagrama UML de secuencia para registrarse")

![Diagrama UML de secuencia para ver tutorial](docs/uml/PPT%20CLASS%20UML%20Tutorial%20Sequence%20Diagram.png "Diagrama UML de secuencia para ver tutorial")

![Diagrama UML de secuencia para jugar en partida local](docs/uml/PPT%20CLASS%20UML%20Local%20match%20Sequence%20Diagram.png "Diagrama UML de secuencia para jugar en partida local")

![Diagrama UML de secuencia para jugar en partida online](docs/uml/PPT%20CLASS%20UML%20Online%20match%20Sequence%20Diagram.png "Diagrama UML de secuencia para jugar en partida online")

## Diagrama de actividades

![Diagrama de actividades - Login](docs/uml/DIAGRAMA%20DE%20ACTIVIDADES%20%E2%80%94%20LOGIN%20(AUTH).png)

![Diagrama de actividades - Registro](docs/uml/DIAGRAMA%20DE%20ACTIVIDADES%20%E2%80%94%20REGISTRO%20(SIGN%20UP).png)

![Diagrama de actividades - Ver tutorial](docs/uml/DIAGRAMA%20DE%20ACTIVIDADES%20%E2%80%94%20VER%20TUTORIAL.png)

![Diagrama de actividades - Partida local](docs/uml/DIAGRAMA%20DE%20ACTIVIDADES%20%E2%80%94%20PARTIDA%20LOCAL.png)

![Diagrama de actividades - Partida online](docs/uml/DIAGRAMA%20DE%20ACTIVIDADES%20%E2%80%94%20PARTIDA%20ONLINE.png)

### Descripción de Casos de Uso

## Caso de uso: Autenticarse

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Autenticarse |
| Actor              | Jugador |
| Descripción        | Permite al jugador autenticarse en el sistema para acceder a las funcionalidades del juego. |
| Precondiciones     | El sistema está en ejecución.<br>El jugador está registrado y activo. |
| Postcondiciones    | El jugador accede al menú principal. |
| Flujo principal    | 1. El jugador selecciona iniciar sesión.<br>2. El sistema solicita credenciales.<br>3. El jugador ingresa usuario y contraseña.<br>4. El sistema valida los datos.<br>5. El sistema concede el acceso. |
| Flujos alternos    | 4a. Usuario no existe.<br>4b. Contraseña incorrecta.<br>4c. Jugador inactivo. |

## Caso de uso: Iniciar sesión

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Iniciar sesión |
| Actor              | Jugador |
| Descripción        | Permite al jugador iniciar sesión mediante credenciales válidas. |
| Precondiciones     | El jugador está registrado. |
| Postcondiciones    | Sesión iniciada correctamente. |
| Flujo principal    | 1. El jugador solicita iniciar sesión.<br>2. El sistema solicita credenciales.<br>3. El jugador ingresa los datos.<br>4. El sistema valida la información.<br>5. El sistema muestra el menú principal. |

## Caso de uso: Ingresar credenciales

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Ingresar credenciales |
| Actor              | Jugador |
| Descripción        | Permite ingresar usuario y contraseña para validación. |
| Precondiciones     | El jugador desea iniciar sesión. |
| Postcondiciones    | Credenciales enviadas para validación. |
| Flujo principal    | 1. El sistema muestra el formulario.<br>2. El jugador ingresa usuario y contraseña.<br>3. El sistema recibe los datos. |

## Caso de uso: Mostrar menú principal

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Mostrar menú principal |
| Actor              | Jugador |
| Descripción        | Muestra las opciones disponibles del sistema. |
| Precondiciones     | El jugador inició sesión correctamente. |
| Postcondiciones    | El jugador puede seleccionar una acción. |
| Flujo principal    | 1. El sistema despliega el menú principal.<br>2. El jugador selecciona una opción. |

## Caso de uso: Ver tutorial

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Ver tutorial |
| Actor              | Jugador |
| Descripción        | Permite visualizar el tutorial del juego. |
| Precondiciones     | El sistema está en ejecución. |
| Postcondiciones    | El jugador conoce las reglas del juego. |
| Flujo principal    | 1. El jugador selecciona ver tutorial.<br>2. El sistema muestra el tutorial. |

## Caso de uso: Ver récords

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Ver récords |
| Actor              | Jugador |
| Descripción        | Permite consultar los récords de partidas anteriores. |
| Precondiciones     | Existen partidas registradas. |
| Postcondiciones    | Récords mostrados al jugador. |
| Flujo principal    | 1. El jugador selecciona ver récords.<br>2. El sistema consulta los datos.<br>3. El sistema muestra los resultados. |

## Caso de uso: Jugar vs máquina

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Jugar vs máquina |
| Actor              | Jugador |
| Descripción        | Permite jugar una partida contra la máquina. |
| Precondiciones     | El jugador ha iniciado sesión. |
| Postcondiciones    | El resultado de la partida queda registrado. |
| Flujo principal    | 1. El jugador selecciona jugar vs máquina.<br>2. El sistema solicita movimiento.<br>3. El sistema genera movimiento de la máquina.<br>4. El sistema evalúa la partida.<br>5. El sistema muestra el resultado. |

## Caso de uso: Seleccionar movimiento

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Seleccionar movimiento |
| Actor              | Jugador |
| Descripción        | Permite elegir el movimiento del jugador. |
| Precondiciones     | Se inició una partida. |
| Postcondiciones    | Movimiento del jugador registrado. |
| Flujo principal    | 1. El sistema muestra opciones.<br>2. El jugador selecciona su movimiento. |

## Caso de uso: Mostrar resultado

| Elemento           | Descripción |
|--------------------|-------------|
| Caso de uso        | Mostrar resultado |
| Actor              | Jugador |
| Descripción        | Muestra el resultado final de la partida. |
| Precondiciones     | Ambos movimientos fueron definidos. |
| Postcondiciones    | El jugador conoce el resultado. |
| Flujo principal    | 1. El sistema evalúa las reglas.<br>2. El sistema determina el ganador.<br>3. El sistema muestra el resultado. |
