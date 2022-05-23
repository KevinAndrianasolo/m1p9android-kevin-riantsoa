var express = require('express');
var router = express.Router();
const path = require('path');
var appDir = path.resolve(__dirname, '..');

//import route 
var company = require('./CompanyRoute');
var asset = require('./AssetRoute');
var courseTheme = require('./CourseThemeRoute');
var course = require('./CourseRoute');
var user = require('./UserRoute');

//all route
router.use('/api/company', company);
router.use('/api/asset', asset);
router.use('/api/courseTheme', courseTheme);
router.use('/api/course', course);
router.use('/api/user', user);
/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('Server is running ...');
});

module.exports = router;
