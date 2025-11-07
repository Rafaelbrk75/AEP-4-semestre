FROM node:18-alpine
WORKDIR /app
ARG VITE_PROXY_TARGET=http://localhost:8080
ENV VITE_PROXY_TARGET=$VITE_PROXY_TARGET
COPY package*.json ./
RUN npm ci --silent
COPY . .
EXPOSE 5173
CMD ["npm","run","dev"]
