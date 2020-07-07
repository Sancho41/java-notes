FROM node

WORKDIR /usr/src

COPY package*.json ./

RUN npm install

ENV PATH /usr/src/node_modules/.bin:$PATH

EXPOSE 3333
