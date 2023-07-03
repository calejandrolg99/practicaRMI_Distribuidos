# Sistema Bancario con Java RMI

## Descripción

Este proyecto es un simple Sistema Bancario implementado con Java RMI y MAVEN. Realiza operaciones bancarias básicas como la creación de cuentas, depósitos y retiros de dinero, y transferencias entre cuentas. Fue creado como una demostración de cómo utilizar Java RMI para crear un sistema distribuido sencillo.

## Instalación

Este proyecto utiliza Java, MAVEN, RMI y JSON (persistencia). Asegúrate Docker instalado en tu máquina.

1. Clona el proyecto a tu máquina local utilizando `git clone https://github.com/calejandrolg99/practicaRMI_Distribuidos`.

2. Construye la imagen del servidor con Docker:
```bash
sudo docker build -t my-server -f server/src/Dockerfile server
```

3. Ejecuta el contenedor del servidor:
```bash
docker run -it -p 1099:1099 --name my-running-server my-server
```

4. Construye la imagen del cliente con Docker:
```bash
sudo docker build -t my-client -f client/src/Dockerfile client
```

5. Ejecuta el contenedor del cliente:
```bash
sudo docker run -it --rm --name my-running-client --link my-running-server my-client
```

## Uso

Después de ejecutar los contenedores del servidor y cliente, puedes realizar varias operaciones bancarias:

- Crear una nueva cuenta: `createAccount(documentNumber, name, username, password, initialAmount)`.
- Obtener detalles de la cuenta: `getAccountDetails(userId, password)`.
- Depositar dinero: `deposit(accountNumber, amount)`.
- Retirar dinero: `withdraw(accountNumber, amount)`.
- Transferir dinero: `transfer(fromAccountNumber, toAccountNumber, amount)`.
