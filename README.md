# Medieval Battle





Jogo de Turno ao estilo D&D


### Installation

Após fazer o checkout do projeto para sua máquina executar


```sh
$ cd medieval-battle-game
$ cd medieval-battle
$ mvn install -DskipTests=true install
```

Após isso, utilizar

```sh
$ cd ..
$ docker-compose up
```
Quando todos os serviços subirem executar
```sh
$ cd medieval-battle-client
$ docker build -t medieval .
$ docker run --network="host" -e URL=127.0.0.1 -it  medieval

```

