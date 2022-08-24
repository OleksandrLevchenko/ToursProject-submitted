document.body.style.backgroundSize = "cover";
document.body.style.backgroundPosition = "center center";
// document.body.style.backgroundImage = "url('/images/KL_day.jpeg')";
//
// function myFunction()
// {
//     var checkBox = document.getElementById("daynighttoggle");
//     if (checkBox.checked != true)
//     {
//         //document.body.style.backgroundColor = "white";
//         document.body.style.backgroundImage = "url('/images/KL_day.jpeg')";
//         document.documentElement.setAttribute('data-theme', 'light');
//     }
//     else
//     {
//         //document.body.style.backgroundColor = "black";
//         document.body.style.backgroundImage = "url('/images/KL_night.jpeg')";
//         document.documentElement.setAttribute('data-theme', 'dark');
//     }
// }

















const currentTheme = localStorage.getItem('theme');
const checkBox = document.getElementById("daynighttoggle");

if (currentTheme) {
    document.documentElement.setAttribute('data-theme', currentTheme);

    if (currentTheme === 'light') {
        document.body.style.backgroundImage = "url('/images/KL_day.jpeg')";
        checkBox.checked = false;
    }
    else {
        document.body.style.backgroundImage = "url('/images/KL_night.jpeg')";
    }
}

function myFunction() {
    if (checkBox.checked) {
        document.body.style.backgroundImage = "url('/images/KL_night.jpeg')";
        document.documentElement.setAttribute('data-theme', 'dark');
        localStorage.setItem('theme', 'dark');
    }
    else {
        document.body.style.backgroundImage = "url('/images/KL_day.jpeg')";
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('theme', 'light');
    }
}

checkBox.addEventListener('change', myFunction, false);








// var select = document.getElementById("selectTown");
// var options = ["Ankara, Turkey", "Casablanca, Morocco", "Chennai, India",
// "Dubai, United Arab Emirates", "Isfahan, Iran", "Karachi, Pakistan",
// "London, United Kingdom", "Malacca City, Malaysia", "Mashhad, Iran"];
//
// for(var i = 0; i < options.length; i++) {
//     var opt = options[i];
//     var sel = document.createElement("option");
//     sel.textContent = opt;
//     sel.value = opt;
//     select.appendChild(sel);
// }