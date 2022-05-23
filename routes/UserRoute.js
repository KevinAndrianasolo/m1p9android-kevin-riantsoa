const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth');
const UserController = require('../controllers/UserController');

router.post("/signup",UserController.user_signup);
router.get("/login",UserController.user_login);
router.get("/profile", checkAuth, UserController.user);
router.get("/:id", checkAuth, UserController.getUserById);
module.exports = router;