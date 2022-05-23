
const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth');
const CourseController = require('../controllers/CourseController');

router.get("/theme/:id",checkAuth, CourseController.getAllCourseByTheme);
router.get("/:id", checkAuth, CourseController.getCourseById);
router.get("/theme/:id/search",  checkAuth,CourseController.getCourseByTitle);

module.exports = router;