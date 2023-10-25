var dotenv = require('dotenv');
var express = require('express');
var http = require('http');
var https = require('https');
var fs = require('fs');
var logger = require('morgan');
var path = require('path');
var router = require('./routes/index');
var auth = require('express-openid-connect').auth;
dotenv.load();
var app = express();
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(logger('dev'));
app.use(express.json());
var externalUrl = process.env.RENDER_EXTERNAL_URL;
var port = externalUrl && process.env.PORT ? parseInt(process.env.PORT) : 4092;
var config = {
    authRequired: false,
    auth0Logout: true,
    secret: process.env.CLIENT_SECRET,
    baseURL: externalUrl || "https://localhost:".concat(port),
    clientID: process.env.CLIENT_ID,
    issuerBaseURL: 'https://dev-y7q23kiz1uhksjri.eu.auth0.com',
};
app.use(auth(config));
// Middleware to make the `user` object available for all views
app.use(function (req, res, next) {
    res.locals.user = req.oidc.user;
    next();
});
app.use('/', router);
if (externalUrl) {
    var hostname_1 = process.env.HOST; //ne 127.0.0.1
    console.log(hostname_1 + " " + port + " " + externalUrl);
    app.listen(port, hostname_1, function () {
        console.log("Server locally running at http://".concat(hostname_1, ":").concat(port, "/ and from\n    outside on ").concat(externalUrl));
    });
}
else {
    https.createServer({
        key: fs.readFileSync('server.key'),
        cert: fs.readFileSync('server.cert')
    }, app)
        .listen(port, function () {
        console.log("Server running at https://localhost:".concat(port, "/"));
    });
}
;
app.use(function (req, res, next) {
    var err = new Error('Not Found');
    err.name = "404";
    next(err);
});
// Error handlers
app.use(function (err, req, res, next) {
    res.status(err.status || 500);
    res.render('index', {
        title: 'Competiton maker',
        isAuthenticated: req.oidc.isAuthenticated()
    });
});
app.post('/newMatch', function (req, res) {
    var userInput = req.body.userInput;
    res.send("You entered: ".concat(userInput));
});
// vJ*6415nQyUf test user password
// testuser@test.com
