require(["jquery"], function() {

    var slide = 0;
    var slidelength = 0;
    $.get("slidecount", function(val){slidelength = val;});
    var video = $("#video").get(0);
    video.preload = "auto";

    // TODO look at HLSJS



    $("#play").click(function () {
        video.play();
        console.log("prev " + slide + " " + slidelength);
    });

    $("#prev").click(function () {
        slide -= 1;
        if (slide < 0) slide = 0;
        video.src = "video/" + slide;
        console.log("prev " + slide + " " + slidelength);
    });
    $("#next").click(function () {
        slide += 1;
        if (slide >= slidelength) slide = slidelength - 1;
        video.src = "video/" + slide;
        console.log("prev " + slide + " " + slidelength);
    });
});