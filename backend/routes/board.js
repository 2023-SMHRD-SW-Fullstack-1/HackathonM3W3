const express = require("express");
const db = require("../config/database");
const router = express.Router();

const conn = db.init();
db.connect(conn);

router.get("/")


module.exports = router;