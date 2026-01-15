function irekiDialogoa() {
  document.getElementById("popup").style.display = "block";
}


function itxiDialogoa() {
  document.getElementById("popup").style.display = "none";
}

function hustuSaskia() {
  document.querySelectorAll("#produktu").forEach(p => {
    p.style.display = "none";
  });
}

