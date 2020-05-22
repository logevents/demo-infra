from http.server import HTTPServer, SimpleHTTPRequestHandler


class RepoRequestHandler(SimpleHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header("Content-type", "text/plain")
        self.end_headers()

    def _encode(self, text):
        return text.encode('utf8')

    def do_POST(self):
        content_length = int(self.headers['Content-Length'])
        body = self.rfile.read(content_length)
        file_name = self.path[1:]
        with open(file_name, "w+b") as f:
            f.write(body)
            f.close()
        self._set_headers()
        self.wfile.write(self._encode(f'{file_name} stored'))


host = 'localhost'
port = 8080

print(f'simple artifact repo running on {host}:{port}')

httpd = HTTPServer((host, port), RepoRequestHandler)
httpd.serve_forever()
