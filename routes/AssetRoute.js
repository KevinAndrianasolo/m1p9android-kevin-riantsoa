const express = require("express");
const router = express.Router();

const AssetController = require('../controllers/AssetController');

router.get("/", AssetController.getAsset);
router.get("/:id", AssetController.getAssetById);
//  router.get("/find/:userId", checkAuth, UserController.findUser);
module.exports = router;