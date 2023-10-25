const dotenv = require('dotenv');
const express = require('express');
const http = require('http');
const https = require('https');
const fs = require('fs');
const logger = require('morgan');
const path = require('path');
const router = require('./routes/index');
const { auth } = require('express-openid-connect');

dotenv.load();

const app = express();

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());

const externalUrl = process.env.RENDER_EXTERNAL_URL;
const port = externalUrl && process.env.PORT ? parseInt(process.env.PORT) : 4092;


const config = {
  authRequired: false,
  auth0Logout: true,
  secret: process.env.CLIENT_SECRET,
  baseURL: externalUrl || `https://localhost:${port}`,
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
  const hostname = '0.0.0.0'; //ne 127.0.0.1
  app.listen(port, hostname, () => {
    console.log(`Server locally running at http://${hostname}:${port}/ and from
    outside on ${externalUrl}`);
    }
  )
} else {
  https.createServer({
  key: fs.readFileSync('server.key'),
  cert: fs.readFileSync('server.cert')
  }, app)
  .listen(port, function () {
  console.log(`Server running at https://localhost:${port}/`);
  });
};

app.use(function (req, res, next) {
  const err = new Error('Not Found');
  err.name = "404";
  next(err);
});

// Error handlers
app.use(function (err, req, res, next) {
  res.status(err.status || 500);
  // res.render('error', {
  //   message: err.message + ` aaaa ` + err.message,
  //   error: process.env.NODE_ENV !== 'production' ? err : {}
  // });
  res.render('index')
});

app.post('/newMatch', (req, res) => {
  const userInput = req.body.userInput;
  res.send(`You entered: ${userInput}`);
});


http.createServer(app)
  .listen(port, () => {
    console.log(`Listening on ${config.baseURL}`);
  });

  // vJ*6415nQyUf test user password
  // testuser@test.com