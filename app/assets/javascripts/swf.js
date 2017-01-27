require(["jquery"], function() {

    var frame = 0;

    swfobject.embedSWF("swf", "video", "550px", "400px", "9");


    $("#prev").click(function () {
        frame -= 1;
        swfobject.gotoAndPlay(frame);
    });

    $("#next").click(function () {
        frame += 1;
        swfobject.gotoAndPlay(frame);
    });


});