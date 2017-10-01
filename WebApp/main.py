"""

LifeSign web app for HackUPC 2017

"""

from flask import Flask, request

app = Flask(__name__, static_url_path='')


@app.route('/')
def root():
    """ Serve the static index.html """
    return app.send_static_file('index.html')


@app.route('/status', methods=['GET', 'POST'])
def status():
    """ AP for the android application """

    if request.method == 'GET':
        print('GET')
        return "You really got me there!"

    if request.method == 'POST':
        print('POST')
        print(request.data)
        return "Hello Chris good to see you alive!"



if __name__ == '__main__':
    app.run()
