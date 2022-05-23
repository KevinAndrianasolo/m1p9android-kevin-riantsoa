const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth');
const UserController = require('../controllers/UserController');

router.post("/signup",UserController.user_signup);
router.post("/login",UserController.user_login);
//router.get("/profile", checkAuth, UserController.user);
router.get("/details", checkAuth, UserController.getUserDetails);
router.get("/:id", UserController.getUserById);
module.exports = router;