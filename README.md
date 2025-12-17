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

## Descripción casos de uso

![Descripción de Casos de Uso](docs/uml/Descripcion%20casos%20de%20uso.pdf)
