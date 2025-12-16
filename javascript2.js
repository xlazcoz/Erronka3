const produktuak = [
  // --- CAMISETAS ---
  {
    productName: "Camiseta blanca",
    price: 15,
    img: "img/camiseta blanca.png",
    category: 'camisetas'
  },
  {
    productName: "Camiseta negra",
    price: 18,
    img: "img/camiseta negra.png",
    category: 'camisetas'
  },
  {
    productName: "Camiseta gris",
    price: 20,
    img: "img/camiseta gris.png",
    category: 'camisetas'
  },
  

  // --- PANTALONES ---
  {
    productName: "Pantalón vaquero",
    price: 40,
    img: "img/pantalon vaquero.png",
    category: 'pantalones'
  },
  {
    productName: "Pantalón beige",
    price: 45,
    img: "img/pantalon beige.png",
    category: 'pantalones'
  },
  {
    productName: "Pantalón chino negro",
    price: 35,
    img: "img/pantalon chino negro.png",
    category: 'pantalones'
  },
  
  // --- CHAQUETAS ---
  {
    productName: "Chaqueta vaquera",
    price: 60,
    img: "img/chaqueta vaquera.png",
    category: 'chaquetas'
  },
  {
    productName: "Chaqueta Bomber Negra",
    price: 80,
    img: "img/Chaqueta Bomber Negra.png",
    category: 'chaquetas'
  },
  {
    productName: "Gabardina Beige con Cinturón",
    price: 75,
    img: "img/Gabardina Beige con Cinturón.png",
    category: 'chaquetas'
  },
  

  // --- ZAPATILLAS ---
  {
    productName: "Zapatillas deportivas blancas",
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

