var router = require('express').Router();
const { requiresAuth } = require('express-openid-connect');

router.get('/', function (req, res, next) {
  res.render('index', {
    title: 'Competiton maker',
    isAuthenticated: req.oidc.isAuthenticated()
  });
});

router.get('/profile', requiresAuth(), function (req, res, next) {
  res.render('profile', {
    userProfile: JSON.stringify(req.oidc.user, null, 2),
    title: 'Profile page'
  });
});

router.get('/matches', requiresAuth(), function (req, res, next) {
  res.render('matches', {
    userProfile: JSON.stringify(req.oidc.user, null, 2),
    title: 'Matches'
  });
});

module.exports = router;
