
const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth');
const CourseController = require('../controllers/CourseController');

router.get("/theme/:id", CourseController.getAllCourseByTheme);
router.get("/:id",  CourseController.getCourseById);
router.get("/theme/:id/search",  CourseController.getCourseByTitle);

module.exports = router;