# dqaas

# Pre-reqs
npm

sbt

# Installation
extract package so that you have a dqaas-client folder and and dqaas-server folder

```bash
cd dqaas-client
```

download dependencies

```bash
npm install
```
start the node server

```bash
npm run dev
```

start server side server

```bash
cd ../dqaas-server
```

run with auto commit (~)

```bash
sbt "~run 9696"
```
