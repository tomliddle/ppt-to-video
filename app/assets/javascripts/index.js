require(["jquery"], function() {

    var swf = $("#video").get(0);
    swf.preload = "auto";

    // TODO look at HLSJS

    $("#play").click(function () {
        swf.play();
    });

    $("#prev").click(function () {
        var currTime = swf.currentTime;
        console.log("prev " + currTime);

        swf.currentTime = currTime - 10;
    });
    $("#next").click(function () {
        var currTime = swf.currentTime;
        console.log("next  " + currTime);
        swf.currentTime = currTime + 10;
    });
});