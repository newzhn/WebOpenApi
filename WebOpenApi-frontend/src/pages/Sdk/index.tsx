import {PageContainer} from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import ReactMarkdown from 'react-markdown';
// @ts-ignore
import markdownFile from '../../../SDK.md';

const SDK: React.FC = () => {
  const [markdownContent, setMarkdownContent] = useState<string>('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true)
    // 加载md文件内容
    fetch(markdownFile)
      .then((response) => response.text())
      .then((text) => setMarkdownContent(text))
      .catch((error) => console.error(error));
    setLoading(false)
  },[])

  return (
    <PageContainer loading={loading}>
      <ReactMarkdown>{markdownContent}</ReactMarkdown>
    </PageContainer>
  );
};

export default SDK;
