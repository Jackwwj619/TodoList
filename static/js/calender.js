// And this is JS Code
const monthE1 = document.querySelector(".date h1");
const fullDateE1 = document.querySelector(".date p");
const DaysE1 = document.querySelector(".days");

const monthInx = new Date().getMonth();
const lastDay = new Date(new Date().getFullYear(), monthInx + 1, 0).getDate();
const firstDay = new Date(new Date().getFullYear(), monthInx - 3, 1).getDay();
console.log(firstDay)

// console.log(monthInx)
const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
];
// console.log(months[monthInx])
monthE1.innerHTML = months[monthInx];
fullDateE1.innerHTML = new Date().toDateString();

let days = "";
for (let i = firstDay; i > 0; i--) {
    days += `<div class="empty"></div>`;
}

for (let i = 1; i <= lastDay; i++) {
    if (i === new Date().getDate()) {
        days += `<div class="today">${i}</div>`;
    }
    else {
        days += `<div>${i}</div>`;
    }
}

DaysE1.innerHTML = days;