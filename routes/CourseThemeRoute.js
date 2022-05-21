
const express = require("express");
const router = express.Router();

const CourseThemeController = require('../controllers/CourseThemeController');

router.get("/:id", CourseThemeController.getCourseThemeByCompany);

module.exports = router;