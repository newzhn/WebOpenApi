import { PageContainer } from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {Badge, Button, Card, Descriptions, Divider, message, Table, Tag, Typography} from "antd";
import moment from "moment";
import {
  getDetailInterfaceInfoUsingGET,
  invokeInterfaceUsingPOST
} from "@/services/WebOpenApi-backend/interfaceController";
import ReactJson from "react-json-view";
import './custom-react-json.css';
import {ColumnsType} from "antd/es/table"; // 引入自定义 CSS 样式
const { Paragraph } = Typography;

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [invokeLoading, setInvokeLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceDetailVo>();
  const [invokeRes, setInvokeRes] = useState<any>('{"code":"响应码","msg":"响应消息","data":"响应结果"}');
  const [requestParams, setRequestParams] = useState<string>('{}');
  const params = useParams()

  const loadData = async () => {
    if (!params.id) {
      message.error("接口不存在或已下线");
      return;
    }
    setLoading(true);
    try {
      const res = await getDetailInterfaceInfoUsingGET({
        id: Number(params.id)
      })
      setData(res.data);
      setRequestParams(res.data.requestParams)
    }catch (error: any) {
      return [];
    }
    setLoading(false);
  }

  useEffect(() => {
    loadData();
  },[])

  //接口调用提交方法
  const doInvoke = async () => {
    if (!params.id) {
      message.error("接口不存在");
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceUsingPOST({
        id: Number(params.id),
        userRequestParams: requestParams,
      })
      setInvokeRes(res.data);
      message.success("调用成功");
    }catch (error: any) {
      return {};
    }
    setInvokeLoading(false);
  }

  const requestColumns: ColumnsType<API.RequestParamsRemarkVO> = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '100px',
    },
    {
      title: '必填',
      key: 'isRequired',
      dataIndex: 'isRequired',
      width: '100px',
    },
    {
      title: '类型',
      dataIndex: 'type',
      width: '100px',
    },
    {
      title: '说明',
      dataIndex: 'remark',
    },
  ];
  const responseColumns: ColumnsType<API.RequestParamsRemarkVO> = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '100px',
    },
    {
      title: '类型',
      dataIndex: 'type',
      width: '100px',
    },
    {
      title: '说明',
      dataIndex: 'remark',
    },
  ];

  return (
    <PageContainer title={"接口在线调试"}>
      <Card loading={loading}>
        {data ? (
          <Descriptions title={data.name} bordered column={4}>
            <Descriptions.Item label="描述" span={4}>{data.description}</Descriptions.Item>
            <Descriptions.Item label="请求URI" span={4}>
              <Paragraph
                copyable={{
                  text: data?.uri,
                }}
                style={{marginBottom: '-4px'}}
              >
                {data.uri}
              </Paragraph>
            </Descriptions.Item>
            <Descriptions.Item label="请求方法" span={1}><Tag color={data.method === 'GET' ? 'success' :
              data.method === 'POST' ? 'processing' : data.method === 'PUT' ? 'warning' : 'error'} key={data.method}>
              {data.method}
            </Tag></Descriptions.Item>
            <Descriptions.Item label="接口状态" span={1}>
              <Badge status={data.status===1?"success":"error"} text={data.status===1?"在线":"已下线"} />
            </Descriptions.Item>
            <Descriptions.Item label="创建时间" span={1}>{moment(data.createTime).format('YYYY-MM-DD HH:mm')}</Descriptions.Item>
            <Descriptions.Item label="更新时间" span={1}>
              {moment(data.updateTime).format('YYYY-MM-DD HH:mm')}
            </Descriptions.Item>

            <Descriptions.Item label="请求头" span={2}>{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="响应头" span={2}>{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="请求参数示例" span={4}>
              <ReactJson name={false} src={JSON.parse(data.requestParams)}/>
            </Descriptions.Item>
            {data.requestParamsRemark && data.requestParamsRemark.length > 0 && (
              <Descriptions.Item  label="请求参数说明" span={4}>
                <Table
                  style={{ width: '100%' }}
                  pagination={{
                    hideOnSinglePage: true,
                  }}
                  columns={requestColumns}
                  dataSource={data.requestParamsRemark}
                />
              </Descriptions.Item>
            )}
            {data.responseParamsRemark && data.responseParamsRemark.length > 0 && (
              <Descriptions.Item label="响应参数说明" span={4}>
                <Table
                  style={{ width: '100%' }}
                  pagination={{
                    hideOnSinglePage: true,
                  }}
                  columns={responseColumns}
                  dataSource={data.responseParamsRemark}
                />
              </Descriptions.Item>
            )}
          </Descriptions>
        ) : (
          <>接口不存在或已下线</>
        )}
      </Card>
      <Divider />
      <Card title={"在线调试"} >
        <ReactJson
          theme="monokai"
          name={false}
          src={JSON.parse(requestParams)}
          displayDataTypes={false}
          onEdit={(edit) => {
            setRequestParams(JSON.stringify(edit.updated_src))
          }}
        />
        {/* 插入空白 div */}
        <div style={{marginBottom: '20px'}}/>
        <Button type={"primary"} onClick={doInvoke}>
          调用
        </Button>
      </Card>
      <Divider />
      <Card title={"调试结果"} loading={invokeLoading}>
        <ReactJson
          theme="monokai"
          name={false}
          src={JSON.parse(invokeRes)}
          collapseStringsAfterLength={100}
          displayDataTypes={false}
        />
      </Card>
    </PageContainer>
  );
};

export default Index;
