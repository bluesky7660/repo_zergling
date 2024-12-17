
var options = {
	chart: {
		width: '100%',
		type: 'pie',
	},
	series: [orderPaied, orderReady, orderdTransit, orderDelivered, orderCanceled],
	labels: ["주문 - 결제완료", "주문 - 준비중", "주문 - 배송중", "주문 - 배송완료", "주문 - 주문취소"],
	legend: {
		position: 'bottom',
	  },
	dataLabels: {
		enabled: false
    },
	  stroke: {
		width: 0,
    },
    colors: ['#4CAF50', '#FF9800', '#2196F3', '#3F51B5', '#F44336'],
}
var chart = new ApexCharts(
	document.querySelector("#basic-pie-graph"),
	options
);
chart.render();


