# dqaas

# Pre-reqs
npm

sbt

# Installation
extract package so that you have a dqaas-client folder and and dqaas-server folder

cd dqaas-client

--download dependencies

npm install

--start the node server

npm run dev

--start server side server

cd ../dqaas-server

--run with auto commit (~)

sbt "~run 9696"
