console.log("ex orderMonthCount:",orderMonthCount[0].orderCount);
let countdata = [];

orderMonthCount.forEach(item => {
    countdata.push(item.orderCount);
});
console.log(countdata);
var options = {
	chart: {
		height: 300,
		type: 'bar',
		dropShadow: {
			enabled: true,
			opacity: 0.1,
			blur: 5,
			left: -10,
			top: 10
		},
        toolbar: {
            show: false // 툴바 숨기기
        }
	},
	plotOptions: {
		bar: {
			dataLabels: {
				position: 'top', // top, center, bottom
			},
		}
	},
	dataLabels: {
		enabled: true,
		formatter: function (val) {
			return val + "건";
		},
		offsetY: -20,
		style: {
			fontSize: '12px',
			colors: ["#2e323c"]
		}
	},
	series: [{
		name: '주문',
		data: countdata
	}],
	xaxis: {
		categories: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		position: 'bottom',
		// labels: {
		// 	offsetY: -18,
		// },
		// axisBorder: {
		// 	show: false
		// },
		// axisTicks: {
		// 	show: false
		// },
		// crosshairs: {
		// 	fill: {
		// 		type: 'gradient',
		// 		gradient: {
		// 			colorFrom: '#435EEF',
		// 			colorTo: '#95c5ff',
		// 			stops: [0, 100],
		// 			opacityFrom: 0.4,
		// 			opacityTo: 0.5,
		// 		}
		// 	}
		// },
		// tooltip: {
		// 	enabled: true,
		// 	offsetY: -35,
		// }
	},
	// fill: {
	// 	gradient: {
	// 		shade: 'light',
	// 		type: "horizontal",
	// 		shadeIntensity: 0.25,
	// 		gradientToColors: undefined,
	// 		inverseColors: true,
	// 		opacityFrom: 1,
	// 		opacityTo: 1,
	// 		stops: [50, 0, 100, 100]
	// 	},
	// },
	// yaxis: {
	// 	axisBorder: {
	// 		show: false
	// 	},
	// 	axisTicks: {
	// 		show: false,
	// 	},
	// 	labels: {
	// 		show: false,
	// 		formatter: function (val) {
	// 			return val + "%";
	// 		}
	// 	}
	// },
	
	grid: {
        borderColor: '#e0e6ed',
        strokeDashArray: 5,
        xaxis: {
            lines: {
                show: true
            }
        },   
        yaxis: {
            lines: {
                show: false,
            } 
        },
        padding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        }, 
    },
	colors: ['#435EEF', '#2b86f5', '#63a9ff', '#95c5ff', '#c6e0ff'],
    // title: {
	// 	text: '2024년도 주문',
	// 	floating: true,
	// 	// offsetY: 320,
	// 	align: 'center',
	// 	style: {
	// 		color: '#2e323c'
	// 	}
	// },
}
var chart = new ApexCharts(
	document.querySelector("#basic-column-graph-datalables"),
	options
);
chart.render();
