FROM alpine:latest

# Run the touch command to create a file

ADD script.sh /script.sh
RUN chmod +x script.sh

# Specify the default command to run when the container starts
CMD ["/script.sh"]
