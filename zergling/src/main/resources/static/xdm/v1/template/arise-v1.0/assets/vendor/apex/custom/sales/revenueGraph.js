var options = {
	chart: {
		height: 317,
		type: 'area',
		toolbar: {
      show: false,
    },
	},
	dataLabels: {
		enabled: false
	},
	stroke: {
		curve: 'smooth',
		width: 3
	},
	series: [{
		name: '2024년 주문',
		data: [10, 40, 15, 40, 20, 35, 20, 10, 31, 43, 56, 29]
	},{
		name: '2024년 주문',
		data: [10, 40, 15, 40, 20, 35, 20, 10, 31, 43, 56, 29]
	},],
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
      bottom: 10,
      left: 0
    }, 
  },
	xaxis: {
		categories: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
	},
	yaxis: {
		labels: {
			show: false,
		}
	},
	colors: ['#4267cd', '#32b2fa'],
	markers: {
		size: 0,
		opacity: 0.1,
		colors: ['#4267cd', '#32b2fa'],
		strokeColor: "#ffffff",
		strokeWidth: 2,
		hover: {
			size: 7,
		}
	},
}

var chart = new ApexCharts(
	document.querySelector("#revenueGraph"),
	options
);

chart.render();