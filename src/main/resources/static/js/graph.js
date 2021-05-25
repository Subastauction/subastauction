  var ctx = document.getElementById("myChart").getContext("2d");
    var myChart = new Chart(ctx, {
        type: "pie",
        data: {
            labels: ['Subastas exitosas', 'subastas'],
            datasets: [{
                label: 'Num datos',
                data: [1, 10],
                backgroundColor: [
                    'rgb(101, 39, 182)',
                    'rgb(28, 91, 209)',
                ],
                borderColor: "#f1f0f0",
                hoverBackgroundColor: "rgba(232,105,90,0.8)",
                hoverBorderColor: "orange",
               
                borderWidth: 2
                
            }]
        }
            ,
        options: {
            plugins: {
                legend: {
                    display: true,
                    labels: {
                        color: 'white'
                    }
                }
            },
            legend: {
                
                labels: {
                    fontColor: "blue",
                    fontSize: 18
                }},
            scales: {
                 
            }
        }
    });
