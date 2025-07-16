
# Kafka Local Setup Guide 🚀

A step-by-step guide to running Apache Kafka locally on macOS, including Zookeeper, Kafka broker, topic creation, and basic producer/consumer usage.

---

## 🧰 Prerequisites

- Java 11 or newer (run `java -version` to confirm)
- Kafka binary distribution (`kafka_2.13-3.9.0`)
- macOS Terminal with Bash or Zsh

---

## 🔌 Start Zookeeper

```bash
cd ~/Coding/kafka_2.13-3.9.0/
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Expected log output includes:

```
INFO clientPortAddress is 0.0.0.0:2181
INFO binding to port /0.0.0.0:2181
```

---

## ⚙️ Start Kafka Broker

In a new terminal window:

```bash
cd ~/Coding/kafka_2.13-3.9.0/
bin/kafka-server-start.sh config/server.properties
```

Expected log output includes:

```
INFO starting (kafka.server.KafkaServer)
INFO Connecting to zookeeper on localhost:2181
```

---

## 🧵 Create Kafka Topic

Open a new terminal:

```bash
cd ~/Coding/kafka_2.13-3.9.0/
bin/kafka-topics.sh   --bootstrap-server localhost:9092   --create   --topic mytopic   --partitions 5   --replication-factor 1
```

You should see:

```
Created topic mytopic.
```

---

## 📤 Start a Kafka Producer

```bash
bin/kafka-console-producer.sh   --bootstrap-server localhost:9092   --topic mytopic
```

Then type messages like:

```
Hello, Kafka!
Test message
```

---

## 📥 Start a Kafka Consumer

In another terminal:

```bash
bin/kafka-console-consumer.sh   --bootstrap-server localhost:9092   --topic mytopic   --from-beginning
```

You’ll see:

```
Hello, Kafka!
Test message
```

---

## 📚 Useful Kafka Commands

### List Topics

```bash
bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
```

### Describe a Topic

```bash
bin/kafka-topics.sh   --bootstrap-server localhost:9092   --describe   --topic mytopic
```

### Delete a Topic

```bash
bin/kafka-topics.sh   --bootstrap-server localhost:9092   --delete   --topic mytopic
```

(*Only works if topic deletion is enabled in server config*)

---

## 🛠️ Troubleshooting

| Problem                          | Solution                                                               |
|----------------------------------|------------------------------------------------------------------------|
| Port already in use              | Stop previous Kafka/Zookeeper instances or change port in config files |
| Topic not found                  | Ensure Kafka broker is running and topic was created                   |
| Messages not received            | Check producer and consumer are using same topic                       |
| Zookeeper/Kafka won't start      | Check Java installation and `/tmp` cleanup                             |

---

## 🧼 Reset / Cleanup

To remove local data and restart from scratch:

```bash
rm -rf /tmp/zookeeper /tmp/kafka-logs
```

---

## 📎 Notes

- Start **Zookeeper first**, then Kafka
- Data is stored by default in `/tmp/kafka-logs` and `/tmp/zookeeper`
- Retention policies and log directories can be configured in `server.properties`