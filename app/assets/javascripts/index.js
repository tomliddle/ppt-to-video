require(["jquery"], function() {
    console.log("boostrap javascript loaded");

    $("#prev").click(function () {
        alert("prev");
    });
    $("#next").click(function () {
        alert("next");
    });
});