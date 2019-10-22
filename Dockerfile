FROM python:3
ADD medieval-battle-client/medieval.py /
RUN pip install requests 
CMD [ "python", "./medieval.py" ]