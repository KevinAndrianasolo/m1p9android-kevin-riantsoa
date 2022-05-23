
const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth');
const CourseThemeController = require('../controllers/CourseThemeController');

router.get("/:id",  CourseThemeController.getCourseThemeByCompany);

module.exports = router;