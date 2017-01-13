require(["jquery"], function() {

  //  var swfObject = $("#video");
    var frame = 0;

    swfobject.embedSWF("video/0", "video", "550px", "400px", "9");

    $("#prev").click(function () {
        frame -= 1;
        swfobject.gotoAndPlay(frame);
    });
    $("#next").click(function () {
        frame += 1;
        swfobject.gotoAndPlay(frame);
    });
});