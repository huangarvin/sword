// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message_package.proto

package com.huangsuip.netty.protobuf;

public final class MessagePackageProto {
  private MessagePackageProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MessagePackageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:MessagePackage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.Header header = 1;</code>
     */
    boolean hasHeader();
    /**
     * <code>.Header header = 1;</code>
     */
    com.huangsuip.netty.protobuf.HeaderProto.Header getHeader();
    /**
     * <code>.Header header = 1;</code>
     */
    com.huangsuip.netty.protobuf.HeaderProto.HeaderOrBuilder getHeaderOrBuilder();

    /**
     * <code>.google.protobuf.Any body = 2;</code>
     */
    boolean hasBody();
    /**
     * <code>.google.protobuf.Any body = 2;</code>
     */
    com.google.protobuf.Any getBody();
    /**
     * <code>.google.protobuf.Any body = 2;</code>
     */
    com.google.protobuf.AnyOrBuilder getBodyOrBuilder();
  }
  /**
   * Protobuf type {@code MessagePackage}
   */
  public  static final class MessagePackage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:MessagePackage)
      MessagePackageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MessagePackage.newBuilder() to construct.
    private MessagePackage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MessagePackage() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new MessagePackage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private MessagePackage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              com.huangsuip.netty.protobuf.HeaderProto.Header.Builder subBuilder = null;
              if (header_ != null) {
                subBuilder = header_.toBuilder();
              }
              header_ = input.readMessage(com.huangsuip.netty.protobuf.HeaderProto.Header.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(header_);
                header_ = subBuilder.buildPartial();
              }

              break;
            }
            case 18: {
              com.google.protobuf.Any.Builder subBuilder = null;
              if (body_ != null) {
                subBuilder = body_.toBuilder();
              }
              body_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(body_);
                body_ = subBuilder.buildPartial();
              }

              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.huangsuip.netty.protobuf.MessagePackageProto.internal_static_MessagePackage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.huangsuip.netty.protobuf.MessagePackageProto.internal_static_MessagePackage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.class, com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.Builder.class);
    }

    public static final int HEADER_FIELD_NUMBER = 1;
    private com.huangsuip.netty.protobuf.HeaderProto.Header header_;
    /**
     * <code>.Header header = 1;</code>
     */
    public boolean hasHeader() {
      return header_ != null;
    }
    /**
     * <code>.Header header = 1;</code>
     */
    public com.huangsuip.netty.protobuf.HeaderProto.Header getHeader() {
      return header_ == null ? com.huangsuip.netty.protobuf.HeaderProto.Header.getDefaultInstance() : header_;
    }
    /**
     * <code>.Header header = 1;</code>
     */
    public com.huangsuip.netty.protobuf.HeaderProto.HeaderOrBuilder getHeaderOrBuilder() {
      return getHeader();
    }

    public static final int BODY_FIELD_NUMBER = 2;
    private com.google.protobuf.Any body_;
    /**
     * <code>.google.protobuf.Any body = 2;</code>
     */
    public boolean hasBody() {
      return body_ != null;
    }
    /**
     * <code>.google.protobuf.Any body = 2;</code>
     */
    public com.google.protobuf.Any getBody() {
      return body_ == null ? com.google.protobuf.Any.getDefaultInstance() : body_;
    }
    /**
     * <code>.google.protobuf.Any body = 2;</code>
     */
    public com.google.protobuf.AnyOrBuilder getBodyOrBuilder() {
      return getBody();
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (header_ != null) {
        output.writeMessage(1, getHeader());
      }
      if (body_ != null) {
        output.writeMessage(2, getBody());
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (header_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getHeader());
      }
      if (body_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, getBody());
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage)) {
        return super.equals(obj);
      }
      com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage other = (com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage) obj;

      if (hasHeader() != other.hasHeader()) return false;
      if (hasHeader()) {
        if (!getHeader()
            .equals(other.getHeader())) return false;
      }
      if (hasBody() != other.hasBody()) return false;
      if (hasBody()) {
        if (!getBody()
            .equals(other.getBody())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasHeader()) {
        hash = (37 * hash) + HEADER_FIELD_NUMBER;
        hash = (53 * hash) + getHeader().hashCode();
      }
      if (hasBody()) {
        hash = (37 * hash) + BODY_FIELD_NUMBER;
        hash = (53 * hash) + getBody().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code MessagePackage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:MessagePackage)
        com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.huangsuip.netty.protobuf.MessagePackageProto.internal_static_MessagePackage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.huangsuip.netty.protobuf.MessagePackageProto.internal_static_MessagePackage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.class, com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.Builder.class);
      }

      // Construct using com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (headerBuilder_ == null) {
          header_ = null;
        } else {
          header_ = null;
          headerBuilder_ = null;
        }
        if (bodyBuilder_ == null) {
          body_ = null;
        } else {
          body_ = null;
          bodyBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.huangsuip.netty.protobuf.MessagePackageProto.internal_static_MessagePackage_descriptor;
      }

      @java.lang.Override
      public com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage getDefaultInstanceForType() {
        return com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.getDefaultInstance();
      }

      @java.lang.Override
      public com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage build() {
        com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage buildPartial() {
        com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage result = new com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage(this);
        if (headerBuilder_ == null) {
          result.header_ = header_;
        } else {
          result.header_ = headerBuilder_.build();
        }
        if (bodyBuilder_ == null) {
          result.body_ = body_;
        } else {
          result.body_ = bodyBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage) {
          return mergeFrom((com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage other) {
        if (other == com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage.getDefaultInstance()) return this;
        if (other.hasHeader()) {
          mergeHeader(other.getHeader());
        }
        if (other.hasBody()) {
          mergeBody(other.getBody());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private com.huangsuip.netty.protobuf.HeaderProto.Header header_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.huangsuip.netty.protobuf.HeaderProto.Header, com.huangsuip.netty.protobuf.HeaderProto.Header.Builder, com.huangsuip.netty.protobuf.HeaderProto.HeaderOrBuilder> headerBuilder_;
      /**
       * <code>.Header header = 1;</code>
       */
      public boolean hasHeader() {
        return headerBuilder_ != null || header_ != null;
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public com.huangsuip.netty.protobuf.HeaderProto.Header getHeader() {
        if (headerBuilder_ == null) {
          return header_ == null ? com.huangsuip.netty.protobuf.HeaderProto.Header.getDefaultInstance() : header_;
        } else {
          return headerBuilder_.getMessage();
        }
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public Builder setHeader(com.huangsuip.netty.protobuf.HeaderProto.Header value) {
        if (headerBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          header_ = value;
          onChanged();
        } else {
          headerBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public Builder setHeader(
          com.huangsuip.netty.protobuf.HeaderProto.Header.Builder builderForValue) {
        if (headerBuilder_ == null) {
          header_ = builderForValue.build();
          onChanged();
        } else {
          headerBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public Builder mergeHeader(com.huangsuip.netty.protobuf.HeaderProto.Header value) {
        if (headerBuilder_ == null) {
          if (header_ != null) {
            header_ =
              com.huangsuip.netty.protobuf.HeaderProto.Header.newBuilder(header_).mergeFrom(value).buildPartial();
          } else {
            header_ = value;
          }
          onChanged();
        } else {
          headerBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public Builder clearHeader() {
        if (headerBuilder_ == null) {
          header_ = null;
          onChanged();
        } else {
          header_ = null;
          headerBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public com.huangsuip.netty.protobuf.HeaderProto.Header.Builder getHeaderBuilder() {
        
        onChanged();
        return getHeaderFieldBuilder().getBuilder();
      }
      /**
       * <code>.Header header = 1;</code>
       */
      public com.huangsuip.netty.protobuf.HeaderProto.HeaderOrBuilder getHeaderOrBuilder() {
        if (headerBuilder_ != null) {
          return headerBuilder_.getMessageOrBuilder();
        } else {
          return header_ == null ?
              com.huangsuip.netty.protobuf.HeaderProto.Header.getDefaultInstance() : header_;
        }
      }
      /**
       * <code>.Header header = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.huangsuip.netty.protobuf.HeaderProto.Header, com.huangsuip.netty.protobuf.HeaderProto.Header.Builder, com.huangsuip.netty.protobuf.HeaderProto.HeaderOrBuilder> 
          getHeaderFieldBuilder() {
        if (headerBuilder_ == null) {
          headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.huangsuip.netty.protobuf.HeaderProto.Header, com.huangsuip.netty.protobuf.HeaderProto.Header.Builder, com.huangsuip.netty.protobuf.HeaderProto.HeaderOrBuilder>(
                  getHeader(),
                  getParentForChildren(),
                  isClean());
          header_ = null;
        }
        return headerBuilder_;
      }

      private com.google.protobuf.Any body_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> bodyBuilder_;
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public boolean hasBody() {
        return bodyBuilder_ != null || body_ != null;
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public com.google.protobuf.Any getBody() {
        if (bodyBuilder_ == null) {
          return body_ == null ? com.google.protobuf.Any.getDefaultInstance() : body_;
        } else {
          return bodyBuilder_.getMessage();
        }
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public Builder setBody(com.google.protobuf.Any value) {
        if (bodyBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          body_ = value;
          onChanged();
        } else {
          bodyBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public Builder setBody(
          com.google.protobuf.Any.Builder builderForValue) {
        if (bodyBuilder_ == null) {
          body_ = builderForValue.build();
          onChanged();
        } else {
          bodyBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public Builder mergeBody(com.google.protobuf.Any value) {
        if (bodyBuilder_ == null) {
          if (body_ != null) {
            body_ =
              com.google.protobuf.Any.newBuilder(body_).mergeFrom(value).buildPartial();
          } else {
            body_ = value;
          }
          onChanged();
        } else {
          bodyBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public Builder clearBody() {
        if (bodyBuilder_ == null) {
          body_ = null;
          onChanged();
        } else {
          body_ = null;
          bodyBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public com.google.protobuf.Any.Builder getBodyBuilder() {
        
        onChanged();
        return getBodyFieldBuilder().getBuilder();
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      public com.google.protobuf.AnyOrBuilder getBodyOrBuilder() {
        if (bodyBuilder_ != null) {
          return bodyBuilder_.getMessageOrBuilder();
        } else {
          return body_ == null ?
              com.google.protobuf.Any.getDefaultInstance() : body_;
        }
      }
      /**
       * <code>.google.protobuf.Any body = 2;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
          getBodyFieldBuilder() {
        if (bodyBuilder_ == null) {
          bodyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                  getBody(),
                  getParentForChildren(),
                  isClean());
          body_ = null;
        }
        return bodyBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:MessagePackage)
    }

    // @@protoc_insertion_point(class_scope:MessagePackage)
    private static final com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage();
    }

    public static com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MessagePackage>
        PARSER = new com.google.protobuf.AbstractParser<MessagePackage>() {
      @java.lang.Override
      public MessagePackage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MessagePackage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MessagePackage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MessagePackage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.huangsuip.netty.protobuf.MessagePackageProto.MessagePackage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MessagePackage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_MessagePackage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025message_package.proto\032\031google/protobuf" +
      "/any.proto\032\014header.proto\"M\n\016MessagePacka" +
      "ge\022\027\n\006header\030\001 \001(\0132\007.Header\022\"\n\004body\030\002 \001(" +
      "\0132\024.google.protobuf.AnyB3\n\034com.huangsuip" +
      ".netty.protobufB\023MessagePackageProtob\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.AnyProto.getDescriptor(),
          com.huangsuip.netty.protobuf.HeaderProto.getDescriptor(),
        });
    internal_static_MessagePackage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_MessagePackage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_MessagePackage_descriptor,
        new java.lang.String[] { "Header", "Body", });
    com.google.protobuf.AnyProto.getDescriptor();
    com.huangsuip.netty.protobuf.HeaderProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
