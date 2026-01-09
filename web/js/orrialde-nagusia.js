const produktuak = [
  // --- CAMISETAS ---
  {
    productName: "Kamiseta Txuria",
    price: 15,
    img: "img/camiseta blanca.png",
    category: 'camisetas'
  },
  {
    productName: "Kamiseta Beltza",
    price: 18,
    img: "img/camiseta negra.png",
    category: 'camisetas'
  },
  {
    productName: "Kamiseta Grisa",
    price: 20,
    img: "img/camiseta gris.png",
    category: 'camisetas'
  },
  

  // --- PANTALONES ---
  {
    productName: "Bakero Galtza",
    price: 40,
    img: "img/pantalon vaquero.png",
    category: 'pantalones'
  },
  {
    productName: "Galtzak Beige",
    price: 45,
    img: "img/pantalon beige.png",
    category: 'pantalones'
  },
  {
    productName: "Txino Galtzak Beltza",
    price: 35,
    img: "img/pantalon chino negro.png",
    category: 'pantalones'
  },
  
  // --- CHAQUETAS ---
  {
    productName: "Bakero Txaketa",
    price: 60,
    img: "img/chaqueta vaquera.png",
    category: 'chaquetas'
  },
  {
    productName: "Bomber Txaketa Beltza",
    price: 80,
    img: "img/Chaqueta Bomber Negra.png",
    category: 'chaquetas'
  },
  {
    productName: "Gabardina Beige Gerrikoarekin",
    price: 75,
    img: "img/Gabardina Beige con Cinturón.png",
    category: 'chaquetas'
  },
  

  // --- ZAPATILLAS ---
  {
    productName: "Kiroletako Zapatila Txuriak",
    price: 50,
    img: "img/Zapatillas deportivas blancas.png",
    category: 'zapatillas'
  },
  {
    productName: "Puma Urban Black",
    price: 60,
    img: "img/Puma Urban Black.png",
    category: 'zapatillas'
  },
  {
    productName: "Converse All Star Grey",
    price: 75,
    img: "img/Converse All Star Grey.png",
    category: 'zapatillas'
  },
  
];

function erakutsiProduktuak(erakustekoProduktuak) {
  const produktuenZerrenda = document.getElementById("produktuenZerrenda")
  
  produktuenZerrenda.innerHTML = ""
  
  erakustekoProduktuak.forEach(produktua => {
    const div = document.createElement("div")
    div.className = 'produktu-txartela'
    
    div.innerHTML = `
      <img src="${produktua.img}" alt="${produktua.productName}">
      <h3>${produktua.productName}</h3>
      <p class="prezioa">$ ${produktua.price}</p>
      <button>Gehitu saskira</button>
    `
    
    produktuenZerrenda.append(div)
  })
}
erakutsiProduktuak(produktuak)

