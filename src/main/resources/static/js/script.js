console.log("Script loaded");

//Change theme work
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
});

//TODO
function changeTheme() {
    //set to web page
    changePageTheme(currentTheme,"");

    //set the listner to change theme button
    const changeThemeButton = document.querySelector('#theme_change_button');

    
    
    changeThemeButton.addEventListener('click', (event) => {       

        let oldTheme=currentTheme;
        console.log("change theme button clicked");

        if (currentTheme === "dark") {
            //theme ko light krna hai
            currentTheme = "light";
        }
        else {
            //theme ko dark krna hai
            currentTheme = "dark";
        }
        console.log(currentTheme);
        
        changePageTheme(currentTheme,oldTheme);
    });
}


//set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

//get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

//change current page theme
function changePageTheme(theme,oldTheme) {
    //localStorage me update karenge
    setTheme(currentTheme);


    //remove the current theme
    if(oldTheme){
        document.querySelector("html").classList.remove(oldTheme);
    }
        
    //set the current theme 
    document.querySelector("html").classList.add(theme);

    //change the text of button
    document.querySelector('#theme_change_button').querySelector("span").textContent =
        theme == "light" ? "Dark" : "Light";
}

//End of change theme work