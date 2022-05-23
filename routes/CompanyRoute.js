const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth');
const CompanyController = require('../controllers/CompanyController');
// const checkAuth = require('../middleware/check-auth');

// router.post("/signup", UserController.user_signup);

// router.post("/login", UserController.user_login);

// router.delete("/delete/:userId", checkAuth, UserController.user_delete);

router.get("/:id",  CompanyController.getCompanyById);
router.get("/",  CompanyController.getCompanies);
//  router.get("/find/:userId", checkAuth, UserController.findUser);
module.exports = router;