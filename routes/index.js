var express = require('express');
var router = express.Router();
const path = require('path');
var appDir = path.resolve(__dirname, '..');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('Server is running ...');
});

module.exports = router;
