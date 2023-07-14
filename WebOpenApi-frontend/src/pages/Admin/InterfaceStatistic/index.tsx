import {
  PageContainer,
} from '@ant-design/pro-components';
import '@umijs/max';
import React, {useEffect, useState} from 'react';
import ReactECharts from 'echarts-for-react';
import {getRankInterfaceInfoVoListUsingGET} from "@/services/WebOpenApi-backend/statisticsController";

/**
 * 接口统计
 * @constructor
 */
const InterfaceStatistic: React.FC = () => {
  const [data,setData] =  useState<API.InterfaceInfoRankVo[]>([])
  const [loading,setLoading] =  useState(true)

  const loadData = async () => {
    setLoading(true);
    try {
      const res = await getRankInterfaceInfoVoListUsingGET()
      if (res.code === 200) {
        setData(res.data);
      }
    }catch (error: any) {
      return [];
    }
    setLoading(false);
  }

  useEffect(() => {
    loadData()
  },[])

  //映射
  const chartData = data.map(item => {
    return {
      value: item.num,
      name: item.name
    }
  })

  const option = {
    title: {
      text: '接口调用统计表',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '接口调用次数',
        type: 'pie',
        radius: '60%',
        data: chartData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  return (
    <PageContainer>
      <ReactECharts showLoading={loading} option={option} />
    </PageContainer>
  );
};
export default InterfaceStatistic;
