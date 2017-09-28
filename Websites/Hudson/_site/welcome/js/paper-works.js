let painting = 1;

$('#back').click(function () {
  painting > 1 ? painting = painting - 1 : painting = 14;
  $("#painting").attr("src", "../../images/paper-works/" + painting + ".jpeg");
})

$('#next').click(function () {
  painting < 14 ? painting = painting + 1 : painting = 1;
  $("#painting").attr("src", "../../images/paper-works/" + painting + ".jpeg");
})
