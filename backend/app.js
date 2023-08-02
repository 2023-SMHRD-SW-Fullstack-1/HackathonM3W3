const express = require("express");
const chatRouter = require("./routes/chat");
const memberRouter = require("./routes/member");
const boardRouter = require("./routes/board");
const app = express();


app.use(express.urlencoded({extended:true}));

app.use("/chat", chatRouter);
app.use("/member", memberRouter);
app.use("/board", boardRouter);

app.set("port", process.env.PORT || 8888);
app.listen(app.get("port"), ()=>{
    console.log(app.get("port"), "번 포트에서 서버연결 기다리는중...");
})