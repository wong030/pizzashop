FROM nginx:1.24.0-alpine-slim


WORKDIR /usr/share/nginx/html

# Copy the local HTML, CSS, JS files to the container
COPY src/main/webapp/ .

# Expose port 80 to the outside world
EXPOSE 80

# Start Nginx when the container launches
CMD ["nginx", "-g", "daemon off;"]
